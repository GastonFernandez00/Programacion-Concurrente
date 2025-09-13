FROM node:22
RUN npm install -g cspell
WORKDIR /app

# Default command to execute CSPELL.
ENTRYPOINT ["cspell"]
