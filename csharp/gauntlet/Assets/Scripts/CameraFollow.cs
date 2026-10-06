using UnityEngine;

// Follows any object in the scene: it only needs the target's Transform (its position), not the whole object.
class CameraFollow : MonoBehaviour
{
    public Transform target;    // set by Game (the composition root); public, because C# members are private by default

    void Update()
    {
        // TODO: put the camera behind and above target.position
    }
}
