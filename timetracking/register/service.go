package register

import "log/slog"

type Service interface {
	Register() (Service, error)
}

type service struct {
	logger *slog.Logger
}
