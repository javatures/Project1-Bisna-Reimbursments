let swapi ='http://localhost:8080/projectdemo/Elogin';


let resp = fetch(swapi,{headers:{'Content-Type':'application/json','Accept':'application/json'}})
.then((resp)=>resp.json())
.then((data)=>{console.log(data);
    
})
.catch(err=>{console.log('Error reading data'+err);});





