 using UnityEngine;
 class Game : MonoBehaviour{
     void Awake(){
        Debug.Log(" test ");
        var player = GameObject.Find("Player").AddComponent<Player>();
        var follow = GameObject.Find("Main Camera").AddComponent<CameraFollow>();
        follow.target = player.transform;
     }
 }