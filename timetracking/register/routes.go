package register

import (
	"github.com/go-chi/chi/v5"
	"github.com/go-chi/chi/v5/middleware"
	"log/slog"
	"net/http"
)

type RouteService interface {
	RegisterRoutes() error
}

type routeService struct {
	logger *slog.Logger
	router chi.Router
}

func NewRouteService(logger *slog.Logger) RouteService {
	router := chi.NewRouter()
	return &routeService{
		logger: logger,
		router: router,
	}
}

func (s *routeService) registerMiddlewares() error {
	s.router.Use(middleware.RealIP, middleware.RequestID, middleware.Logger, middleware.Recoverer)
	return nil
}

func (s *routeService) Router() chi.Router {
	return s.router
}

func (s *routeService) RegisterRoutes() error {
	if err := s.registerMiddlewares(); err != nil {
		return err
	}

	// SignUp Route
	s.router.Post("/signup", signUp)

	return nil
}

func signUp(w http.ResponseWriter, r *http.Request) {

	w.WriteHeader(http.StatusOK)
	_, _ = w.Write([]byte("user signed up successfully"))
}
