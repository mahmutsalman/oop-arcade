 using UnityEngine;
 class FirstPersonLook : MonoBehaviour{
    //this class is for camera itself.
    public Transform body;
    float sensitivity = 3f;
    float pitch;
    void Start()
    {
        Cursor.lockState=CursorLockMode.Locked;
    }
    void Update()
    {
        float x = Input.GetAxis("Mouse X");
        body.Rotate(0,x*sensitivity,0);
        float y = Input.GetAxis("Mouse Y");
        this.transform.Rotate(-y*sensitivity,0,0);
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            Cursor.lockState=CursorLockMode.None;
        }
    }
 }