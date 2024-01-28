package register

import "log/slog"

type Service interface {
	Register() error
}

type service struct {
	logger *slog.Logger
}

func NewService(logger *slog.Logger) Service {
	return &service{
		logger: logger,
	}
}

func (s *service) Register() error {

	return nil
}
