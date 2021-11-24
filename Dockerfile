FROM openjdk:16-alpine

RUN apk add python3 gcc musl-dev

WORKDIR /usr/src/compiler

COPY . .

ENTRYPOINT [ "python3", "birlang.py" ]