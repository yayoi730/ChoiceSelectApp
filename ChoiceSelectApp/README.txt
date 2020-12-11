Introduction:
This program runs a Choice Select Application on a web page. The user is able to 
create a choice, add feedback, approve or disapprove, and choose a choice or 
alternative as final. The administrator page is designed to display reports of 
all choices present in the database and have the ability to delete old reports.

Created by Team TeX:
Erin Carter, Ryan Doyle, Brendan Grady, and Liliana Foucault

Run by: 
Amazon Web Services

Links:
(User Login)
https://s3.us-east-2.amazonaws.com/choice.select.app/html/userLogin.html
(Administrator Page)
https://s3.us-east-2.amazonaws.com/choice.select.app/html/adminPage.html


Instructions:

Navigate to the user login page where you can enter a choice ID, name, and optional 
password to login to a choice as a new member. You will be redirected to the choice 
interface page if successfully logged in.

If you would like to create a new choice, click "Create New Choice" instead at the
bottom. You will be asked to provide a name, optional password, and values for the
choice. This includes a description, max team size, and at least two alternative 
descriptions. You will be redirected to the choice interaction page once the choice
is created successfully.

Once you are on the choice interaction page you are able to view the choice 
credentials at the top. To view an alternative and its credentials, there are buttons
to toggle viewing. You may add feedback and like or dislike an alternative. Any
member may also complete a choice by marking either an alternative or the main choice
as chosen. Once a choice has been completed, users can no longer interact with the
choice. The choice interaction page will become view-only and display that it has 
been completed.

If you navigate to the administrator page, you will be able to view the reports of 
all choices created. It will display the choice ID, description, completion date, 
and whether or not the choice has been finalized. The admin may also delete old 
reports by submitting a certain number of days old. 






