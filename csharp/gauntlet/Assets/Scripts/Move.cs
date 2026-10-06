using UnityEngine;
class Move
{
    Transform body;
    public Move(Transform transform)
    {
     this.body = transform;   
    }
    
    // moves only; doesn't care where the direction came from (keys today, chat commands later)
    public void move(float speed, Vector3 direction)
    {
        body.position += direction.normalized * speed * Time.deltaTime;
    }
}