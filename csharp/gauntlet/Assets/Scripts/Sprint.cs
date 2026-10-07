using UnityEngine;
class Sprint : IMovement
{
    Transform body;
    float speedMultiplier = 2;
    public Sprint(Transform transform)
    {
     this.body = transform;   
    }
    
    // moves only; doesn't care where the direction came from (keys today, chat commands later)
    public void move(float speed, Vector3 direction)
    {
        Vector3 worldDirection = body.forward * direction.z + body.right * direction.x;   // same as Move (copied: see the note)
        body.position += worldDirection.normalized * speed * speedMultiplier * Time.deltaTime;
    }
}