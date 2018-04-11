README File

This project has the assessment code required by PayClip.
To run/test the application, the following must be done:

* After clonning the project, inside the folder "PayClipAssessment", move to the "dist" folder.
* Inside "dist" you will find the "application" file that is in charge of running the application.

  Simple exapmples on Linux:
	$ ./application 345 add '{ "amount": 1.23, "description": "Joes Tacos", "date": "2018-12-30", "user_id": 345 }'
	$ ./application 345 ec54f936-69b7-47f8-9cac-d838d31f97b6
	$ ./application 345 list
	$ ./application 345 sum

The file with the application data is called "data.txt" and will be creatd at: ../PayClipAssessment/dist 
