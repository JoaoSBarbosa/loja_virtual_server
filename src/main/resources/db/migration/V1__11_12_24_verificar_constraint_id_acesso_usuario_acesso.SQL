SELECT constraint_name
FROM information_schema.constraint_column_usage
WHERE table_name = 'usuario_acesso'
  AND column_name = 'id_acesso'
  AND constraint_name <> 'unique_acesso_user';