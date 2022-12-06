#Stage 1
#Build angular app

FROM node:16.17-alpine3.15 As build
#Create a virtual directory inside the image
WORKDIR /dist/src/app

RUN npm cache clean --force

#Copy files to image virtual directory
COPY . .

RUN npm install
RUN npm run build --prod


#Stage 2
#Configuring nginx
FROM nginx:latest AS ngi

#Compiled code from previuos stage
COPY --from=build /dist/src/app/dist/app-vestibulando /usr/share/nginx/html

COPY /nginx.conf  /etc/nginx/conf.d/default.conf

EXPOSE 80
