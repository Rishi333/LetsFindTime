// Your Client ID can be retrieved from your project in the Google
      // Developer Console, https://console.developers.google.com
      var CLIENT_ID = '750799222243-6l0jjmbk3s0e49062co8jmgdmhm19bf1.apps.googleusercontent.com';

      var SCOPES = ["https://www.googleapis.com/auth/calendar.readonly"];

      /**
       * Check if current user has authorized this application.
       */
      function checkAuth() {
        gapi.auth.authorize(
          {
            'client_id': CLIENT_ID,
            'scope': SCOPES.join(' '),
            'immediate': true
          }, handleAuthResult);
      }

      /**
       * Handle response from authorization server.
       *
       * @param {Object} authResult Authorization result.
       */
      function handleAuthResult(authResult) {
        var authorizeDiv = document.getElementById('authorize-div');
        if (authResult && !authResult.error) {
          // Hide auth UI, then load client library.
          authorizeDiv.style.display = 'none';
          loadCalendarApi();
        } else {
          // Show auth UI, allowing the user to initiate authorization by
          // clicking authorize button.
          authorizeDiv.style.display = 'inline';
        }
      }

      /**
       * Initiate auth flow in response to user clicking authorize button.
       *
       * @param {Event} event Button click event.
       */
      function handleAuthClick(event) {
        gapi.auth.authorize(
          {client_id: CLIENT_ID, scope: SCOPES, immediate: false},
          handleAuthResult);
        return false;
      }

      /**
       * Load Google Calendar client library. List upcoming events
       * once client library is loaded.
       */
      function loadCalendarApi() {
        gapi.client.load('calendar', 'v3', listUpcomingEvents);
      }

      /**
       * Print the summary and start datetime/date of the next ten events in
       * the authorized user's calendar. If no events are found an
       * appropriate message is printed.
       */
      function listUpcomingEvents() {
      	var today = new Date();
   	 	var nextweek = new Date(today.getFullYear(), today.getMonth(), today.getDate()+7);
    
        var request = gapi.client.calendar.events.list({
          'calendarId': 'primary',
          'timeMin': today.toISOString(),
          'timeMax': nextweek.toISOString(),
          'showDeleted': false,
          'singleEvents': true,
          'orderBy': 'startTime'
        });

        request.execute(function(resp) {
          var events = resp.items;
          appendPre('Upcoming events:');

          var text = ""
          if (events.length > 0) {
            for (i = 0; i < events.length; i++) {
              var event = events[i];
              var startTime = event.start.dateTime;
              if (!startTime) {
                startTime = 'ALL-DAY-START~'+event.start.date
              }
              var endTime = event.end.dateTime;
              if (!endTime) {
              	endTime = 'ALL-DAY-END~'+event.end.date
              }
              // appendPre(event.summary + ' (' + startTime + ')')
              text += startTime +'|'+endTime + "$"
//              appendPre(startTime +'|'+endTime)
            }
            appendPre(text)
          } else {
            appendPre('No upcoming events found.');
          }

   //        	xmlhttp = new XMLHttpRequest();
			// var url = "http://localhost:8000/test";
			// xmlhttp.open("POST", url, true);
			// xmlhttp.setRequestHeader("Content-type", "application/json");
			// var parameters = JSON.stringify({"username":"myname","password":"mypass"});
			// xmlhttp.send(parameters);

        });
      }

      /**
       * Append a pre element to the body containing the given message
       * as its text node.
       *
       * @param {string} message Text to be placed in pre element.
       */
      function appendPre(message) {
        var pre = document.getElementById('output');
        var textContent = document.createTextNode(message + '\n');
        pre.appendChild(textContent);
        console.log(message)
//        console.log(textContent)
//        console.log(pre)
        var pw = document.getElementById('nametmp')
//        console.log(pw)
        pw.value = message
      }
      
      function showMessage(){
    	   
    	   var pw = document.getElementById('nametmp')
    	   console.log(pw.value)
    	   alert(pw.value);
    	}
