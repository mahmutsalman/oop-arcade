 using UnityEngine;
 class Player : MonoBehaviour{
    float speed = 5f;                                       // metres per second
     void Awake(){
        Debug.Log(" from Player class ");
     }
     void Update()
    {
        Vector3 direction = ReadDirection();                // job 1: which way?
        Move(direction);                                    // job 2: go that way
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

    // moves only; doesn't care where the direction came from (keys today, chat commands later)
    void Move(Vector3 direction)
    {
        transform.position += direction * speed * Time.deltaTime;
    }
 }
