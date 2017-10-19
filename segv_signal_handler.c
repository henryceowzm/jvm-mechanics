#include<stdio.h>
#include <sys/types.h>
#include<signal.h>
#include<unistd.h>

void sig_handler(int signo)
{
  if (signo == SIGSEGV)
    printf("received SIGSEGV\n");
    kill(getpid(),SIGTERM);
}

int main(void)
{
  if (signal(SIGSEGV, sig_handler) == SIG_ERR)
    printf("\ncan't catch SIGSEGV\n");
  int *p=NULL;
  printf("Dereferencing a NULL pointer(%d), which will cause SIGSEGV",*p);
  sleep(1000);
  return 0;
}
