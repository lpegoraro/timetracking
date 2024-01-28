package cmd

import (
	"context"
	"errors"
	"flag"
	"fmt"
	"log/slog"
	"os"
	"os/signal"
	"syscall"
	"timetracking/timetracking"
)

func main() {
	logLevelFlag := flag.String("log_level", "info", "Output log level")
	flag.Parse()
	opts := &slog.HandlerOptions{
		Level: levelFromString(*logLevelFlag),
	}
	logger := slog.New(slog.NewJSONHandler(os.Stdout, opts))
	slog.SetDefault(logger)

	c, cancel := context.WithCancelCause(context.Background())
	defer cancel(errors.New("main context canceled"))

	// Initialize Main Service
	mainService := timetracking.NewMainService(c, logger)
	if err := mainService.Start(); err != nil {
		logger.Error("main service failed", err)
		cancel(err)
	}

	sigs := make(chan os.Signal, 1)
	signal.Notify(sigs, os.Interrupt, os.Kill, syscall.SIGTERM, syscall.SIGINT, syscall.SIGQUIT, syscall.SIGKILL)
	select {
	case <-c.Done():
		cause := context.Cause(c)
		logger.Info("main context done with cause: ", cause, c.Err())
	case sig := <-sigs:
		cancel(errors.New(fmt.Sprintf("terminate Signal %s received", sig.String())))
	}
}

func levelFromString(level string) slog.Level {
	switch level {
	case "debug":
		return slog.LevelDebug
	case "info":
		return slog.LevelInfo
	case "warn":
		return slog.LevelWarn
	case "error":
		return slog.LevelError
	default:
		return slog.LevelInfo
	}
}
