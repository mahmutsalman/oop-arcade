using UnityEngine;
class Move : IMovement
{
    Transform body;
    public Move(Transform transform)
    {
     this.body = transform;   
    }
    
    // moves only; doesn't care where the direction came from (keys today, chat commands later)
    public void move(float speed, Vector3 direction)
    {
        // the brain's direction is "for me" (z = forward, x = right); turn it into world space using where the body faces
        Vector3 worldDirection = body.forward * direction.z + body.right * direction.x;
        body.position += worldDirection.normalized * speed * Time.deltaTime;
    }
}