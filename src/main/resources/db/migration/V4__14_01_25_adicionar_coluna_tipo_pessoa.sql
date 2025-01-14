DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'pessoa_fisica' 
        AND column_name = 'tipo_pessoa'
    ) THEN
ALTER TABLE pessoa_fisica ADD COLUMN tipo_pessoa CHARACTER VARYING(255);
END IF;

    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'pessoa_juridica' 
        AND column_name = 'tipo_pessoa'
    ) THEN
ALTER TABLE pessoa_juridica ADD COLUMN tipo_pessoa CHARACTER VARYING(255);
END IF;
END $$;
