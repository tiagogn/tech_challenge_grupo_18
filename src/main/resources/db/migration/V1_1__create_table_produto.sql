create table if not exists "produto"(
    id uuid primary key,
    nome text not null,
    preco numeric not null,
    categoria text not null
);