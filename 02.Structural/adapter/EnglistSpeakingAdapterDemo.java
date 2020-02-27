package com.jpm.structural.adapter;

public class EnglistSpeakingAdapterDemo {

	public static void main(String[] args) {
		 //create a teacher
        Teacher Megan = new Teacher();
        
        //Create An English Speaker object
        EnglishSpeakerKid Elizabeth = new EnglishSpeakerKid();
        Elizabeth.setName("Elizabeth");
        Elizabeth.setAge(3) ;
        Elizabeth.setFavorFood("Chicken Nuggets")  ;

        //teacher Megan starts talking with Elizabeth
        System.out.println("Miss Megan starts talking to an English Speaker");
        Megan.setObjectToTalk(Elizabeth);
        Megan.StartChatting();
        System.out.println();
        
        //Use Adapter since John can't speak English.
        AdapterForNonEnglishSpeaker John = new AdapterForNonEnglishSpeaker();
        //Teacher Megan starts talking with John
        System.out.println("Miss Megan starts talking to a Non English Speaker");
        Megan.setObjectToTalk(John);
        Megan.StartChatting();
        System.out.println();      
   

	}

}

//Target methods declaration.
interface ITalkable
{
    //In our case study, the following method means that Teacher can only talk in English.
    void TellMeAboutNameInEnglish();
    void TellMeAboutAgeInEnglish();
    void TellMeAboutFavorFoodInEnglish();
}
//Adapter for Non English Speaker
class AdapterForNonEnglishSpeaker implements ITalkable
{
    
    NonEnglishSpeaker aNonEnglishSpeakerKid = new NonEnglishSpeaker();


   public void TellMeAboutNameInEnglish()
    {
        //call helper method for client to distigilish between an Adapter and Regular ITalkable objects
        aNonEnglishSpeakerKid.getNaam();
        System.out.println("My name is "+ aNonEnglishSpeakerKid.getNaam());
    }

    public void TellMeAboutAgeInEnglish()
    {
        aNonEnglishSpeakerKid.getUmar();
        System.out.println("I am "+aNonEnglishSpeakerKid.getUmar()+" years old" );
    }

    public void TellMeAboutFavorFoodInEnglish()
    {
        System.out.println("My favor food is "+ aNonEnglishSpeakerKid.getKhana());
    }

}

//(Adaptee) that is going to be used as a outside system component in our demo
class NonEnglishSpeaker
{	
    private String naam ;
    private int umar ;    
    private String khana ;

    //constructor assign the default values for demo purpose. 
    public NonEnglishSpeaker()
    {
    	naam = "John";
    	umar = 4;
    	khana = "Pasta";
    }

    //helper method for demo purpose.
    public void MethodA()
    {
        System.out.println("Hello! I am a Non-English Speaker");
    }

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getUmar() {
		return umar;
	}

	public void setUmar(int umar) {
		this.umar = umar;
	}

	public String getKhana() {
		return khana;
	}

	public void setKhana(String khana) {
		this.khana = khana;
	}

	
}
//regular ITalkable class that system can directly talk to.
class EnglishSpeakerKid implements ITalkable
{
	private String name ;
    private int age ;    
    public String FavorFood ;


    public void TellMeAboutNameInEnglish()
    {
        System.out.println("My name is "+ name);
    }

    public void TellMeAboutAgeInEnglish()
    {
        System.out.println("I am "+ age +"  years old");
    }

    public void TellMeAboutFavorFoodInEnglish()
    {
        System.out.println("My favor food is "+ FavorFood);
    }

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFavorFood() {
		return FavorFood;
	}
	public void setFavorFood(String favorFood) {
		FavorFood = favorFood;
	}
}
interface ICommunicator
{
    //build a brige between a Communicator and a talkable object (Kid)
	public ITalkable getObjectToTalk();
	public void setObjectToTalk(ITalkable objectToTalk);

    //Start chatting process
    void StartChatting();
}
class Teacher implements ICommunicator
{
	private ITalkable ObjectToTalk; 
	
    //Implementing the Chatting procedures.
    public void StartChatting()
    {
        System.out.println("What's your name?");
        ObjectToTalk.TellMeAboutNameInEnglish();

        System.out.println("How old are you?");
        ObjectToTalk.TellMeAboutAgeInEnglish();

        System.out.println("What's your favor food");
        ObjectToTalk.TellMeAboutFavorFoodInEnglish();
    }

	public ITalkable getObjectToTalk() {
		return ObjectToTalk;
	}

	public void setObjectToTalk(ITalkable objectToTalk) {
		ObjectToTalk = objectToTalk;
	}

}