 using UnityEngine;
 class Player : MonoBehaviour{
    float speed = 5f;
     void Awake(){
        Debug.Log(" from Player class ");
     }
     void Update()
    {
        if (Input.GetKey(KeyCode.W))
        {   
            this.transform.position += new Vector3(0,0,1) * Time.deltaTime * speed;
        }
    }
 }