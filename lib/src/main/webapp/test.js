let allPeople = []
let swapi = 'http://localhost:8080/projectdemo/hello';
 function get(url) {
    return new Promise(function(resolve, reject) {
        let req = new XMLHttpRequest();
        req.open('get', url, true);

        req.onload = function() {
            if (req.status == 200) {
                resolve(req.response)
            } else {
                reject(Error(req.statusText))
            }         };

         req.onerror = function() {
            reject(Error("Network Error"))
        };
         req.send();
     })
 }

 get(swapi)
     .then(function(response) {
        allMovies = JSON.parse(response);
         console.log(allMovies)
     })
     .catch(function(err) {
         console.log(err)
     }); 