<script>
  import { user } from '../stores/userStore.js'; // import the Svelte store
  let username = '';
  let password = '';
  let error = '';

  async function login() {
    error = '';
    try {
      const res = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
      });

      if (!res.ok) {
        const data = await res.json();
        error = data.message || 'Login failed';
        return;
      }

      const loggedInUser = await res.json();
      user.set(loggedInUser); // <- update the store directly

      // Optionally clear the form
      username = '';
      password = '';
      error = '';
    } catch (err) {
      error = 'Server unreachable';
      console.error(err);
    }
  }
</script>

<style>
  .login-container {
    max-width: 400px;
    margin: auto;
    padding: 2rem;
    border: 1px solid #ccc;
    border-radius: 6px;
  }
  input {
    display: block;
    width: 100%;
    margin: 0.5rem 0;
    padding: 0.5rem;
  }
  button {
    padding: 0.5rem 1rem;
    margin-top: 1rem;
  }
  .error {
    color: red;
    margin-top: 0.5rem;
  }
</style>

<div class="login-container">
  <h2>Admin Login</h2>
  <input type="text" placeholder="Username" bind:value={username} />
  <input type="password" placeholder="Password" bind:value={password} />
  <button on:click={login}>Login</button>
  {#if error}<div class="error">{error}</div>{/if}
</div>