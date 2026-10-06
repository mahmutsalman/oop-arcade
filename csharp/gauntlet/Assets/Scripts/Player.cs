 using UnityEngine;
 class Player : MonoBehaviour{
    float speed = 5f;                                       // metres per second
    Move move;
    Sprint sp;
    KeyboardBrain kb;
     void Awake(){
        Debug.Log(" from Player class ");
        move = new Move(this.transform);  
        sp = new Sprint(this.transform);
        kb = new KeyboardBrain();
     }
     void Update()
    {
        Vector3 direction = kb.Decide();               // job 1: which way?                                   // job 2: go that way
        IMovement current = isShiftPressed() ? sp : move;
        current.move(speed,direction);
    }
    
    bool isShiftPressed()
    {
       return Input.GetKey(KeyCode.LeftShift) ? true : false; 
    }

 }
