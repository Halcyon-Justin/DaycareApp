import svelte from 'rollup-plugin-svelte';
import commonjs from '@rollup/plugin-commonjs';
import terser from '@rollup/plugin-terser';
import resolve from '@rollup/plugin-node-resolve';
import livereload from 'rollup-plugin-livereload';
import css from 'rollup-plugin-css-only';
import serve from 'rollup-plugin-serve';

const production = !process.env.ROLLUP_WATCH;

export default {
	input: 'src/main.js',
	output: {
		sourcemap: true,
		format: 'iife',
		name: 'app',
		file: 'public/build/bundle.js'
	},
	plugins: [
		svelte({ compilerOptions: { dev: !production } }),
		css({ output: 'bundle.css' }),
		resolve({ browser: true, dedupe: ['svelte'], exportConditions: ['svelte'] }),
		commonjs(),
		!production && serve({ contentBase: 'public', port: 5173 }), // ← dev server on 5173
		!production && livereload('public'),
		production && terser()
	],
	watch: { clearScreen: false }
};