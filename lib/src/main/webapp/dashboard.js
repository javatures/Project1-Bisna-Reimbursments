let swapi ='http://localhost:8080/projectdemo/hello';
let resp = fetch(swapi,{
    
    headers:{'Content-Type':'application/json','Accept':'application/json'}

})
.then((resp)=>resp.json())
.then((data)=>{console.log(data);
    console.log(data.sup_id);
    console.log(data.sup_fn);
    console.log(data.sup_ln);
    })
   
.catch(err=>{console.log('Error reading data'+err);});