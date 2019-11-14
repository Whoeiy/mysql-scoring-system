#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <semaphore.h>

sem_t g_semt;

void* work_thread()
{
	pthread_t tid=pthread_self();
	printf("-----%d is waiting for a semaphore ------\n", (unsigned int)tid);
	sem_wait(&g_semt);
	printf("-----%d got a semaphore, is running ------\n", (unsigned int)tid);
	sleep(2);
	sem_post(&g_semt);
	
	static char* pRet="Thread finished!\n";
	return pRet;
}

int main()
{
	int count =5;
	int i;
	int semCount = 2;
	int nRet = -1;
	void* pRet = NULL;
	pthread_t threadIDs[5]={0,0,0,0,0};

	nRet = sem_init(&g_semt, 0,semCount);
	if(nRet!=0)
		return -1;

	for(i=0; i<count; i++)
	{
		nRet = pthread_create(&threadIDs[i], NULL, work_thread, NULL);
		if(nRet!=0)
			continue;
	}
	
	for(i=0;i<count; i++)
	{
		int nRet2 = pthread_join(threadIDs[i], &pRet);
		printf("%d return value is %s\n",(unsigned int)threadIDs[i],(char*)pRet);	
	}
	sem_destroy(&g_semt);
	return 0;
}
