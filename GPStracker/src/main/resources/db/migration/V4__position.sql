CREATE TABLE IF NOT EXISTS position (
                                        id          BIGSERIAL PRIMARY KEY,
                                        vehicle_id  UUID NOT NULL REFERENCES vehicle(id) ON DELETE CASCADE,
    fix_time    TIMESTAMPTZ NOT NULL,
    lat         DOUBLE PRECISION NOT NULL,
    lon         DOUBLE PRECISION NOT NULL,
    speed_kph   DOUBLE PRECISION,
    heading_deg DOUBLE PRECISION
    );

CREATE INDEX IF NOT EXISTS idx_position_vehicle_time
    ON position (vehicle_id, fix_time DESC);
