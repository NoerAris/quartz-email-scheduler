# quartz-email-scheduler
Send email scheduled by application using springboot, swagger and MySql database
# In this project also include cron job scheduler to schedule other task

1. Create database mysql : quartz_demo
2. Execute script : script.sql in folder \quartz-demo\src\main\resources
3. Change username and password database based in your database configuration
4. Change mail username in .yml file based on your email
5. Change mail password in .yml file
# Note
Go to https://myaccount.google.com/security?pli=1#connectedapps
#Set ‘Allow less secure apps’ to YES
#Mail password was generated by gmail based on your password email

# Sample hit REST use postman
Method : Post
Host : localhost:8023/scheduleEmail
body -> raw:
{
    "email" : "ariss.asn@gmail.com",
    "subject" : "Quartz Scheduler",
    "body" : "This email send by java application.",
    "dateTime" : "2020-07-24T09:29:00",
    "timeZone" : "Asia/Jakarta"
}
Format : JSON

You can see sample in image in : \src\main\resources\hit rest.png

# Or you can access this rest api via swaggger in : localhost:8023/swagger-ui.html
