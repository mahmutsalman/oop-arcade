 using UnityEngine;
 class FirstPersonLook : MonoBehaviour{
    //this class is for camera itself.
    public Transform body;
    float sensitivity = 3f;
    float pitch = 0;
    void Start()
    {
        Cursor.lockState=CursorLockMode.Locked;
    }
    void Update()
    {
        float x = Input.GetAxis("Mouse X");
        body.Rotate(0,x*sensitivity,0);
        float y = Input.GetAxis("Mouse Y");
        pitch+= -y*sensitivity;
        pitch = Mathf.Max(-85,Mathf.Min(pitch,85));
        this.transform.localRotation = Quaternion.Euler(pitch, 0, 0);
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            Cursor.lockState=CursorLockMode.None;
        }
    }
 }