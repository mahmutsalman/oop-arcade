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
        body.position += direction.normalized * speed * speedMultiplier * Time.deltaTime;
    }
}