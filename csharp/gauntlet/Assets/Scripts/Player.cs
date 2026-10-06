 using UnityEngine;
 class Player : MonoBehaviour{
    float speed = 5f;                                       // metres per second
    Move move;
     void Awake(){
        Debug.Log(" from Player class ");
        move = new Move(this.transform);  
     }
     void Update()
    {
        Vector3 direction = ReadDirection();                // job 1: which way?                                   // job 2: go that way
        move.move(speed,direction);
    }

    // reads the keys only; never moves anything
    Vector3 ReadDirection()
    {
        Vector3 direction = Vector3.zero;
        if (Input.GetKey(KeyCode.W)) direction += Vector3.forward;
        if (Input.GetKey(KeyCode.S)) direction += Vector3.back;
        if (Input.GetKey(KeyCode.A)) direction += Vector3.left;
        if (Input.GetKey(KeyCode.D)) direction += Vector3.right;
        return direction;
    }
    
 }
