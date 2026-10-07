 using UnityEngine;
 class Game : MonoBehaviour{
     void Awake(){
        Debug.Log(" test ");
        var player = GameObject.Find("Player").AddComponent<Player>();
        player.Init(new KeyboardBrain());
        var look = GameObject.Find("Main Camera").AddComponent<FirstPersonLook>();
        look.body = player.transform;
        look.transform.SetParent(player.transform);
        // eye height, just in front of the visor; Quaternion.identity = no extra rotation (face where the player faces)
        look.transform.SetLocalPositionAndRotation(new Vector3(0, 1.7f, 0.5f), Quaternion.identity);
     }
 }