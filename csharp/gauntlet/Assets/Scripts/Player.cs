 using UnityEngine;
 class Player : MonoBehaviour{
     void Awake(){
        Debug.Log(" from Player class ");
     }
     void Update()
    {
        if (Input.GetKey(KeyCode.W))
        {   
            this.transform.position += new Vector3(0,0,1);
        }
    }
 }