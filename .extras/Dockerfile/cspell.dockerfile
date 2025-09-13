FROM node:22
RUN npm install -g cspell
WORKDIR /app

# Comando por defecto al ejecutar el contenedor
ENTRYPOINT ["cspell"]
