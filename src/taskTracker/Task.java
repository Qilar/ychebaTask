package taskTracker;

public class Task {
        String nameQuest;
        boolean completedOrNot;

        public Task(String nameQuest){
            this.nameQuest = nameQuest;
            completedOrNot = false;
        }

        public String toString (){
            if(completedOrNot == false){
               return "[ ] " + this.nameQuest;
            }else{
               return  "[X] " + this.nameQuest;
            }
        }

        public void complete(boolean b){
            this.completedOrNot = b;
        }
}
