 using UnityEngine;
 class Game : MonoBehaviour{
     void Awake(){
        Debug.Log(" test ");
        var player = GameObject.Find("Player").AddComponent<Player>();
        player.Init(new KeyboardBrain());
        var look = GameObject.Find("Main Camera").AddComponent<FirstPersonLook>();
        look.body = player.transform;
     }
 }