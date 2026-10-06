 using UnityEngine;
 class Player : MonoBehaviour{
    float speed = 5f;                                       // metres per second
    Move move;
    Sprint sp;
    IBrain brain;
     void Awake(){
        Debug.Log(" from Player class ");
        move = new Move(this.transform);  
        sp = new Sprint(this.transform);
        
     }
     void Update()
    {
        Vector3 direction = brain.Decide();               // job 1: which way?                                   // job 2: go that way
        IMovement current = isShiftPressed() ? sp : move;
        current.move(speed,direction);
    }
    public void Init(IBrain brain)
   {
      this.brain = brain;
   }
    
    bool isShiftPressed()
    {
       return Input.GetKey(KeyCode.LeftShift) ? true : false; 
    }

 }
