FROM nginx:1.25.2-alpine AS ngi
#RUN apt-get update && apt-get -y install cron nano
# Copying compiled code and nginx config to different folder
# NOTE: This path may change according to your project's output folder
COPY /dist/main-site /usr/share/nginx/html
COPY /configs/nginx.conf  /etc/nginx/conf.d/default.conf
COPY /configs /eureka
RUN chmod +x /eureka/eureka.sh
#RUN echo "* * * * * /eureka/eureka.sh >> /eureka/eureka.log" >> /etc/crontabs/root

#RUN crontab -l > mycron
#RUN echo "* * * * * /eureka/eureka.sh >> /eureka/eureka.log" >> mycron
#RUN crontab mycron
#RUN rm mycron
#RUN touch /var/log/cron.log
#CMD /etc/init.d/cron start && tail -f /var/log/cron.log
#RUN update-rc.d cron enable
#RUN update-rc.d cron defaults
#CMD cron && tail -f /var/log/cron.log
#CMD service cron start
EXPOSE 8080
