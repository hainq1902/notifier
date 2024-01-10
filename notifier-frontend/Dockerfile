# Build Stage
FROM node:20-alpine as build
WORKDIR /notifier-frontend/
COPY public/ /notifier-frontend/public
COPY src/ /notifier-frontend/src
COPY package.json /notifier-frontend/
RUN npm install
RUN npm run build

# Package stage
FROM nginx:stable-alpine3.17
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=build /notifier-frontend/build .
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]



