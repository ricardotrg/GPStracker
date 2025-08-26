CREATE TABLE IF NOT EXISTS share_link_public (
                                                 id         UUID PRIMARY KEY,
                                                 vehicle_id UUID NOT NULL REFERENCES vehicle(id) ON DELETE CASCADE,
    token      TEXT NOT NULL UNIQUE,
    expires_at TIMESTAMPTZ,
    is_active  BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
    );
