package timetracking

import (
	ctx "context"
	"log/slog"
	"timetracking/timetracking/register"
)

type MainService interface {
	Start() error
	InitializeAPI() error
	ConnectToDB() error
	ConnectToImmutableDB() error
}

type mainService struct {
	logger *slog.Logger
	// Main Go Routine Context
	mainCtx    ctx.Context
	cancelFunc ctx.CancelCauseFunc
}

func NewMainService(c ctx.Context, logger *slog.Logger) MainService {
	c, cancel := ctx.WithCancelCause(c)
	return &mainService{
		logger:     logger,
		mainCtx:    c,
		cancelFunc: cancel,
	}
}

func (s *mainService) Start() error {
	if err := s.InitializeAPI(); err != nil {
		return err
	}
	if err := s.ConnectToDB(); err != nil {
		return err
	}
	if err := s.ConnectToImmutableDB(); err != nil {
		return err
	}
	return nil
}

func (s *mainService) InitializeAPI() error {
	registerService := register.NewService(s.logger)
	if err := registerService.Register(); err != nil {
		return err
	}

	return nil
}

func (s *mainService) ConnectToDB() error {
	return nil
}

func (s *mainService) ConnectToImmutableDB() error {
	return nil
}
