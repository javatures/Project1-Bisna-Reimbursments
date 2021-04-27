let allPeople=[]
let swapi = 'http://localhost:8080/projectdemo/hello';
(async()=>{

let resp = await fetch(swapi)
allPeople = await resp.json()
console.log(allpeople)
document.querySelector('#greeting').innerHTML = PeopleListDiv(allPeople.results)

})();

function PeopleDiv(peopledata) {
return `<div>

<h2>supid: ${peopledata.sup_id} </h2>
<h3>supfn: ${peopledata.sup_fn}</h3>
<h3>supln: ${peopledata.sup_ln}</h3>
</div>`

}

function PeopleListDiv(peoplejson){

return `<div>
    ${peoplejson.map(PeopleDiv).join('')}
    </div> `

}

