#!/bin/bash

# Verifica se è stato fornito un parametro
if [ -z "$1" ]; then
    echo "Errore: specificare un nome come parametro."
    exit 1
fi

# Leggi il parametro
nome=$1

# Stampa il messaggio di benvenuto
echo "Bentornato, $nome!"
