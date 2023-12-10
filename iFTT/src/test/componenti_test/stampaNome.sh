#!/bin/bash

# Verifica se è stato fornito un parametro
if [ -z "$1" ]; then
    echo "Errore: specificare un nome come parametro."
    exit 1
fi

# Leggi il parametro
nome=$1

# Verifica se il nome è "Andrea"
if [ "$nome" == "Andrea" ]; then
    echo "Errore: il nome non può essere Andrea."
    exit 1
fi

# Stampa il messaggio di benvenuto
echo "Bentornato, $nome!"
