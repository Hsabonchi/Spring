//sd

var MongoClient = require('mongodb').MongoClient;
var readlineSync = require('readline-sync');



async function mainMenu() {
    var client=  await MongoClient.connect("mongodb://cs5220stu12:MARp56Jg7dEe@ecst-csproj2.calstatela.edu:6317/cs5220stu12",{ useUnifiedTopology: true });
var db = client.db("cs5220stu12"); 
    var collection= db.collection("users")
    var number=0;
    
    
    collection.find({}).toArray(function(error, users) {
console.log("Main Menu:\n")
var fNameArr=[];
    var lNameArr=[];
    var idsArr=[];
users.forEach(element => {
console.log((number + 1) + ") " + element['firstName'] + " " + element['lastName']);
            fNameArr.push(element['firstName']);
            lNameArr.push(element['lastName']);
            idsArr.push(element['_id']);
            number++;
})
console.log("x) Exit");
 var choice=readlineSync.question('Please Enter your Choice: ');
   //console.log('You have entered Option ' + options);
if(choice=="x"){
console.log("Exited");

//to exit
process.exit(1)
    }
        else {
            let selectedIndex=choice-1
 console.log(fNameArr[selectedIndex]+' '+lNameArr[selectedIndex])
 secondMenu(fNameArr[selectedIndex]+" "+lNameArr[selectedIndex], idsArr[selectedIndex])
}
 
});
}
mainMenu();
async function secondMenu(name, idFromMainMenu){
    var client=  await MongoClient.connect("mongodb://cs5220stu12:MARp56Jg7dEe@ecst-csproj2.calstatela.edu:6317/cs5220stu12",{ useUnifiedTopology: true });
var db = client.db("cs5220stu12"); 
    var wholename=name.split(" ");
    var fName=wholename[0]
    var lName=wholename[1]
    var ucoll=db.collection("users")
    console.log("User - " + fName+' '+lName)
    var queList=["1) List the articles authored by the user","2) Change first name","3) Change last name","4) Change email","b) Back to Main Menu"]
    for(var i=0;i<queList.length;i++){
        console.log(queList[i]+"\n")
    }

var selectedOption=readlineSync.question('Enter your Choice: ');
if(selectedOption == 'b') {
    //go back to mainmenu
    mainMenu()
}

 if(selectedOption == '1') {
var artCollections= db.collection("articles")
artCollections.find({author:idFromMainMenu}).toArray(function(error, user) {
    user.forEach(element => {
    console.log("\n"+element['title'])
    })});
    secondMenu(name, idFromMainMenu)
}
    else if(selectedOption == '2') {
        firstnameFromUser=readlineSync.question('Enter your firstName:');
        ucoll.findOneAndUpdate({_id:idFromMainMenu}, {$set:{firstName:firstnameFromUser},function (error,user) {
            if(error)
            console.log(error);
        }})
        secondMenu(firstnameFromUser+" "+lName, idFromMainMenu)
    }
    else if(selectedOption == '3') {
        lastnameFromUser=readlineSync.question('Enter your lastname:');
        ucoll.findOneAndUpdate({_id:idFromMainMenu}, {$set:{lastName:lastnameFromUser},function (error,user) {
            if(error)
            console.log(error);
        }})
        secondMenu(fName+" "+lastnameFromUser, idFromMainMenu)
    }
    else if(selectedOption == '4') {
 userEmail=readlineSync.questionEMail('Enter new Email:');
 ucoll.findOneAndUpdate({_id:idFromMainMenu}, {$set:{email:userEmail},function (error,user) {
    if(error)
    console.log(error); 
}})
secondMenu(name, idFromMainMenu)
    }
     
    
   
    
    

}
