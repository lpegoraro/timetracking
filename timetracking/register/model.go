package register

import "time"

type entity struct {
	CreatedAt time.Time
	UpdatedAt time.Time
}

type Team struct {
	entity
	Id   string
	Name string
}

type User struct {
	entity
	Id       string
	Username string
	Email    string
}

type Role struct {
	entity
	Id          string
	Description string
}
