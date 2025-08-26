CREATE TABLE vehicle (
                         id UUID PRIMARY KEY,
                         tenant_id UUID NULL,
                         name TEXT NOT NULL,
                         plate TEXT,
                         imei TEXT UNIQUE,
                         created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                         is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE share_link (
                            id UUID PRIMARY KEY,
                            tenant_id UUID NULL,
                            label TEXT,
                            vehicle_ids UUID[] NOT NULL,
                            created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
                            expires_at TIMESTAMPTZ NOT NULL,
                            can_view_history BOOLEAN NOT NULL DEFAULT FALSE,
                            is_active BOOLEAN NOT NULL DEFAULT TRUE
);
