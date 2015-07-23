##Spring Boot + MongoDB + Heroku##
This demo application has been created as an example of using Spring Boot + MongoDB deployed on Heroku.


###Live Demo###
Be aware that the template is currently running on a free Heroku account.  If it hasn't been accessed in 30 minutes, then ***the first request will take up to 60 seconds***.  Please be patient with the first request. Subsequent requests will be normal.  

Here is the [MongoDB Developer's Bookshelf](https://developer-bookshelf-heroku.herokuapp.com/) running on Heroku.


###Local Deployment###
Load a local MongoDB database on port 27017.

```
$ mvn clean install  
$ mvn spring-boot:run
```

Navigate to [http://localhost:8080](http://localhost:8080).  
 
The application can also be deployed by running the `Application.java` class.

###Deploying to Heroku###
<i>The following steps require that the [Heroku Toolbelt](https://toolbelt.heroku.com/) has been installed locally and that a Heroku account has been created.</i>

Navigate to the project directory on the command line.

Before creating your Heroku application, make sure that there is a Git repository associated with the project.   
```
$ git status
```  

If a Git repository is not associated with the project, then create one before continuing. 

Create a new application on Heroku  
```
$ heroku create
```

Rename your Heroku application if interested  
``` 
$ heroku apps:rename new-name
```

Add a MongoDB database to your Heroku application with MongoLab.
Note that your Heroku account must have a credit card attached in order to use free add-ons other than the PostgreSQL and MySQL add-ons.
```
$ heroku addons:create mongolab:sandbox
```

Deploy project to Heroku  
```
$ git push heroku master
```

Look at your application logs to see what is happening behind the scenes  
```
$ heroku logs
```

If your application deploys without timing out then open it as follows 
```
$ heroku open
```

###Special Thanks###
A big thank you to [Julien Dubois](http://www.julien-dubois.com/) and the [JHipster](https://jhipster.github.io/) project for leading the way in the rapid development of Spring applications, as well as for part of the foundation for this template.  JHipster is a Yeoman generator used to create Spring + AngularJS projects, with full hot reload of Java and JavaScript code.

Also, thank you to Rafal Borowiec's for his impressive [spring-mvc-quickstart-archetype](https://github.com/kolorobot/spring-mvc-quickstart-archetype) project.


### Author ###
[Chris Bailey](http://www.chrisbaileydeveloper.com)