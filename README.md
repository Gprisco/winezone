# Winezone
Java and Tomcat Web application for TSW course @ Università degli Studi di Salerno

## Description of the project
An e-commerce for selling wine.
The project wants to demonstrate concepts like session handling, database connection handling with the _Driver Manager Connection Pool_ technique, usage of tomcat services (like jndi, servlet contexts)
for managing app's configuration.

Frontend has been built with the usage of a custom Bootstrap build and jQuery for validating and giving custom feedbacks to the user through DOM manipulation. 
Some AJAX calls have been made to exchange information without triggering a full page refresh.
Some custom css has been written to build custom components.

**Note**: the payment is not real, no real services (e.g. Stripe...) have been implemented.
