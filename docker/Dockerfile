FROM golang:1.21.5-alpine3.14 as builder

RUN apk --update add ca-certificates git update-ca-certificates

WORKDIR /build

RUN go build -o /build/timetracking ./cmd/timetracking

FROM scratch

COPY --from=builder /etc/ssl/certs/ca-certificates.crt /etc/ssl/certs/
COPY --from=builder /build/timetracking /app/timetracking

ENTRYPOINT ["/app/timetracking"]
