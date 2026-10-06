 using UnityEngine;
 class Player : MonoBehaviour{
    float speed = 5f;                                       // metres per second
     void Awake(){
        Debug.Log(" from Player class ");
     }
     void Update()
    {
        if (Input.GetKey(KeyCode.W))
        {   
            this.transform.position += Vector3.forward * Time.deltaTime * speed;   // (0, 0, 1): toward the gate
        }
        if (Input.GetKey(KeyCode.S))
        {
            this.transform.position += Vector3.back * Time.deltaTime * speed;      // (0, 0, -1)
        }
        if (Input.GetKey(KeyCode.A))
        {
            this.transform.position += Vector3.left * Time.deltaTime * speed;      // (-1, 0, 0)
        }
        if (Input.GetKey(KeyCode.D))
        {
            this.transform.position += Vector3.right * Time.deltaTime * speed;     // (1, 0, 0)
        }
    }
 }
