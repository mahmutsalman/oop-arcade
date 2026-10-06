 using UnityEngine;
 class Game : MonoBehaviour{
     void Awake(){
        Debug.Log(" test ");
        GameObject.Find("Player").AddComponent<Player>();
     }
 }