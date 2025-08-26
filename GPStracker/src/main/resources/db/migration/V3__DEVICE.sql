CREATE TABLE IF NOT EXISTS device (
                                      id          UUID PRIMARY KEY,
                                      vehicle_id  UUID NOT NULL REFERENCES vehicle(id) ON DELETE CASCADE,
    imei        TEXT NOT NULL UNIQUE,
    api_key     TEXT NOT NULL UNIQUE,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    is_active   BOOLEAN NOT NULL DEFAULT TRUE
    );